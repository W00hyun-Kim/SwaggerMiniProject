
package member.service;


import org.springframework.beans.factory.annotation.Autowired;
import java.security.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import member.dto.Member;
import member.repository.MemberRepository;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    //가입하기
    public Member save(Member member) {
    	//기존에 가입 아이디가 존재한다면
    	if(findByUserId(member.getUserId()).isPresent()) {
    		throw new IllegalStateException("아이디가 존재합니다.");
    	}
    	
        return memberRepository.save(member);
    }

    //조회하기(회원 전체 정보 받기)
    public List<Member> findAll () {
        return memberRepository.findAll();
    }

    //조회하기(특정 ID의 회원 정보만 받기)
    public Optional<Member> findByUserId (String userId) {
        return memberRepository.findByUserId(userId);
    }
    
    public void delete(String userId) {
    	memberRepository.deleteByUserId(userId);
    }
    
    //회원정보 업데이트(모든 정보 한번에 수정) key - ID
    public Member update(Member member) {
    	
    	Member selected = findByUserId(member.getUserId()).get();
    	//회원 아이디가 존재하지 않는 경우
    	if(selected == null) {
    		throw new NoSuchElementException();
    	}    	
    	selected.setUserId(member.getUserId());
    	selected.setPassword(member.getPassword());
    	selected.setName(member.getName());
    	selected.setBirth(member.getBirth());
    	selected.setAddress(member.getAddress());
    	selected.setGender(member.getGender());
    	selected.setEmail(member.getEmail());
    	selected.setPhone(member.getPhone());
    	
    	return memberRepository.save(selected);
 	   	
    }

    //회원정보 업데이트(특정 필드만 수정) , 수정 필드명과 값 지정
    public Member patch(Member member) {
    	Member selected = findByUserId(member.getUserId()).get();
    	//회원 아이디가 존재하지 않는 경우
    	if(selected == null) {
    		throw new NoSuchElementException();
    	}     	
  	
    	//내가 바꾸고자 하는 게 패쓰워드면 받아온 member 값이 null값이 아니지
    	if(member.getPassword() != null) {
    		selected.setPassword(member.getPassword());
    	}
    	if(member.getName() != null) {
    		selected.setName(member.getName());
    	}
    	if(member.getBirth() != null) {
    		selected.setBirth(member.getBirth());
    	}
    	if(member.getAddress() != null) {
    		selected.setAddress(member.getAddress());
    	}
    	if(member.getGender() != null) {
    		selected.setGender(member.getGender());
    	}
    	if(member.getEmail() != null) {
    		selected.setEmail(member.getEmail());
    	}
    	if(member.getPhone() != null) {
    		selected.setPhone(member.getPhone());
    	}
   	   	
    	return memberRepository.save(selected);
    }
    
    //비밀번호 암호화
    public String encrypt(String pw) throws Exception {
    	String str = "";
    	try {
    		MessageDigest sh = MessageDigest.getInstance("SHA-256");
    		sh.update(pw.getBytes());
    		byte byteData[] = sh.digest();
    		StringBuffer sb = new StringBuffer();
    		for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
    		str = sb.toString();
    	} catch (NoSuchElementException e) {
    		e.printStackTrace();
    		str = null;
    	}
    	return str;
    }
    
    
    //핸드폰번호 '-'없애기
    public String phoneNumForm(String phoneNum) {
    	String changedForm = phoneNum.replace("-", "");
    	
    	return changedForm;
    }
    
    //이메일 체크 
	public void isValidEmail(String email) {
		boolean err = false;
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		if (m.matches()) {
			err = true;
		}
		if(err == false) {
			throw new NoSuchElementException();
		}
	}
	
    
}