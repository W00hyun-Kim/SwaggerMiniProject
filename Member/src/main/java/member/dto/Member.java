package member.dto;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter @Setter
public class Member {
		
    @Id   
    @Column
    @ApiParam(value = "아이디 입력(예시 : kamyamy)", required = true)
    private String userId;
    
	@Column
    @ApiParam(value = "비밀번호 입력(예시 : asdf123)", required = true)
    private String password;

    @Column
    @ApiParam(value = "이름 입력(예시 : 김우현)", required = true)
    private String name;

	@Column
    @ApiParam(value = "생년월일 입력(예시 : 19950905)", required = true)
    private String birth;

    @Column
    @ApiParam(value = "주소 입력(예시 : 경기도 성남시)", required = true)
    private String address;

    @Column
    @ApiParam(value = "성별 입력(예시 : 여자)", required = true)
    private String gender;

    @Column
    @ApiParam(value = "이메일 입력(예시 : amy@naver.com)", required = true)
    private String email;

    @Column
    @ApiParam(value = "핸드폼번호 입력(예시 : 010-4128-9509)", required = true)
    private String phone;
   
}
