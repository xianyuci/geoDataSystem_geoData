package com.hz.GeoDataSystem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
	//private static final String    cookieVlue="{\"Internal\":\"1\",\"PORTALSSOUser\":\"10242281\",\"PORTALSSOCookie\":\"a5b6f5b4f8a921192b6ed6e6001344ab\",\"PORTALSSOLanguage\":\"zh_CN\",\"USERNAMEEN\":\"\",\"USERNAME\":\"\",\"COMPUTER_FNO\":\"201707730140\"}";
	private static final String    cookieVlue="ceshi-cookie";
	@Bean
	 public Docket webApi() {
		
		ParameterBuilder token = new ParameterBuilder();
    	ParameterBuilder lang = new ParameterBuilder();
    	ParameterBuilder userid = new ParameterBuilder();
    	ParameterBuilder tenantid = new ParameterBuilder();
    	//ParameterBuilder servicename = new ParameterBuilder();
    	ParameterBuilder cookie = new ParameterBuilder();
    	List<Parameter> pars = new ArrayList<Parameter>();

    	//所有接口默认请求头。框架默认都是非必须，应根据实际情况设置每个头的 required 属性来指明是否必须项。
    	token.name("X-Lang-Id").description("语言标准编码").modelRef(new ModelRef("string")).parameterType("header").required(false).defaultValue("zh_CN").build();
    	lang.name("X-Emp-No").description("用户账号").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
    	userid.name("X-Org-Id").description("用户ID").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
    	tenantid.name("X-Auth-Value").description("token值").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
    	//servicename.name("X-Origin-ServiceName").description("调用方微服务名").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		cookie.name("cookies").description("cookies").modelRef(new ModelRef("string")).parameterType("header").required(false).defaultValue(cookieVlue).build();

		pars.add(token.build());
    	pars.add(lang.build());
    	pars.add(tenantid.build());
    	//pars.add(servicename.build());
		pars.add(userid.build());
		pars.add(cookie.build());
		
	 return new Docket(DocumentationType.SWAGGER_2)
	  .groupName("GeoDataSystem接口文档")
	  .apiInfo(apiInfo())
	  .select()
	  .apis(RequestHandlerSelectors.basePackage("com.hz.GeoDataSystem.interfaces.controller"))
	  .paths(PathSelectors.any())
	  .build().globalOperationParameters(pars);
	  //.build();
	 }
	 /**
	 swagger2使用说明：
	  测试用例地址：http://localhost.zte.com.cn:9090/swagger-ui.html#
	 @Api：用在类上，说明该类的作用
	 @ApiOperation：用在方法上，说明方法的作用
	 @ApiIgnore：使用该注解忽略这个API
	 @ApiImplicitParams：用在方法上包含一组参数说明
	 @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
	 paramType：参数放在哪个地方
	 header-->请求参数的获取：@RequestHeader
	 query-->请求参数的获取：@RequestParam
	 path（用于restful接口）-->请求参数的获取：@PathVariable
	 body（不常用）
	 form（不常用）
	 name：参数名
	 dataType：参数类型
	 required：参数是否必须传
	 value：参数的意思
	 defaultValue：参数的默认值
	 @ApiResponses：用于表示一组响应
	 @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
	 code：数字，例如400
	 message：信息，例如"请求参数没填好"
	 response：抛出异常的类
	 @ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
	 @ApiModelProperty：描述一个model的属性
	 */
		
	private ApiInfo apiInfo() {
	 return new ApiInfoBuilder()
	  .title("GeoDataSystem使用Swagger2构建RESTful APIs")
	  //.description("众源标报服务")
	  //.contact(new Contact("郝振", "http://petstore.swagger.io/v2/swagger.json", "992414698@qq.com"))
	  .version("1.0")
	  .build();
	 }
}
