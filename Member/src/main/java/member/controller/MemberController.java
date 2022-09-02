package member.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;
import member.dto.Member;
import member.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/member")
@Api(tags =  "회원가입 API")
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberService memberService;
    
    //회원 등록
    @PostMapping(value="")
    @ApiOperation(value = "등록 처리", notes = "신규 회원 등록이 가능합니다.")
    public Member save(Member member) {
    	
    	return memberService.save(member);
    }
 
    
    //전체 조회
    @GetMapping(value = "") // user 테이블의 모든 정보를 읽어옴
    @ApiOperation(value = "회원 전체 조회", notes = "모든 회원의 상세 정보를 조회할 수 있습니다.") 
    public List<Member> getList() {        	   	
    	return memberService.findAll();
    }

    
    //하나 조회
    @GetMapping(value="/{userId}")
    @ApiOperation(value = "회원 하나 조회", notes = "아이디를 가지고 회원의 상세 정보를 조회할 수 있습니다.")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "userId", value = "회원 아이디", example = "amy9595")
    })
    public Member oneView(@PathVariable String userId) {
    	Member member = memberService.findByUserId(userId).get();   	
    	
    	return member;
    }
    
    //전체 업데이트 (덮어쓰기)
    @PutMapping(value="/{userId}")
    @ApiOperation(value = "회원 업데이트", notes = "아이디를 가지고 회원의 상세정보를 업데이트할 수 있습니다.")
    
    public Member update(@PathVariable("userId") String userId, @RequestBody Member member) {
    	   	   	
    	return memberService.update(member);
    }
    
    //업데이트(특정부분)
    @PatchMapping(value="/{userId}")
    @ApiOperation(value = "회원 특정 정보 업데이트", notes = "아이디를 가지고 회원의 특정 정보를 업데이트할 수 있습니다.")

    public Member update_part(@RequestBody Member member) {
   	
    	return memberService.patch(member);
   	    	
    }
           
  
    //삭제 처리
    @DeleteMapping(value="/delete/{userId}")
    @ApiOperation(value = "회원 하나 삭제", notes = "아이디를 가지고 회원의 정보를 삭제할 수 있습니다.")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "userId", value = "회원 아이디", example = "amy9595")
    })
    public boolean delete(@PathVariable String userId) {
    	Member member = memberService.findByUserId(userId).get();
    	if(member == null) {
    		return false;
    	}
    	memberService.delete(userId);
    	return true;   	
    	
    }
    
    //회원아이디가 존재하지 않을 때 발생하는 예외처리
    @ExceptionHandler(NoSuchElementException.class)
    public Object nullEx(Exception e) {
       
       return "회원 아이디가 존재하지 않습니다.";
    }

}