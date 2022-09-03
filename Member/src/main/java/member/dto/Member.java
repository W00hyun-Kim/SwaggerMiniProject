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
    @ApiModelProperty(example = "kamyamy9509")
    private String userId;
    
	@Column
    @ApiModelProperty(example = "asdf123")
    private String password;

    @Column
    @ApiModelProperty(example = "김우현")    
    private String name;

	@Column
    @ApiModelProperty(example = "19950905")    	
    private String birth;

    @Column
    @ApiModelProperty(example = "경기도 성남시")        
    private String address;

    @Column
    @ApiModelProperty(example = "여자")            
    private String gender;

    @Column
    @ApiModelProperty(example = "abc@naver.com")                
    private String email;

    @Column
    @ApiModelProperty(example = "010-4128-9509")                
    private String phone;
   
}
