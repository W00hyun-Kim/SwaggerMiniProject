package member.dto;


import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(example = "가입하고자 하는 아이디")
    private String userId;
    
	@Column
    @ApiModelProperty(example = "비밀번호 입력")
    private String password;

    @Column
    @ApiModelProperty(example = "이름 입력(예: 김우현)")    
    private String name;

	@Column
    @ApiModelProperty(example = "생년월일 입력(예: 19950905)")    	
    private String birth;

    @Column
    @ApiModelProperty(example = "주소 입력(예: 경기도 성남시)")        
    private String address;

    @Column
    @ApiModelProperty(example = "성별 입력(예: 여자)")            
    private String gender;

    @Column
    @ApiModelProperty(example = "이메일 입력(예: abc@naver.com)")                
    private String email;

    @Column
    @ApiModelProperty(example = "핸드폰 번호 입력(예: 010-4128-9509)")                
    private String phone;
   
}
