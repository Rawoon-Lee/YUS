package com.ssafit.yus.controller;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafit.yus.model.dto.UserInfo;
import com.ssafit.yus.model.dto.UserInfoWithFile;
import com.ssafit.yus.model.dto.YoutubeLiked;
import com.ssafit.yus.model.service.UserInfoService;
import com.ssafit.yus.util.JWTUtil;
import com.ssafit.yus.util.SHA256;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "회원정보 관련 api")
@RestController
@RequestMapping("/user")
public class UserRestController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String PATH = new File("").getAbsolutePath();
	private static final String macOS = "/../front/vue-ssafit-yus/src/assets/UserInfo/";
	private static final String windowOS = "\\..\\front\\vue-ssafit-yus\\src\\assets\\UserInfo\\";
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private JWTUtil jwtUtil;
	
	@ApiOperation(
			value = "로그인",
			notes = "id와 password를 받아서 DB와 일치하는지 확인 후 access-token을 클라이언트에게 전달."
					+ "id : 사용자 id, msg : success or fail, status : 상태코드"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userInfo", value = "id & pw를 담은 userInfo객체", dataTypeClass = UserInfo.class, required = true)
	})
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody UserInfo userInfo) {
		HttpStatus status = HttpStatus.ACCEPTED;
		String msg = FAIL;
		HashMap<String, Object> result = new HashMap<>();
		try { 
			String id = userInfo.getUserId();
			String pw = new SHA256().getHash(userInfo.getUserPassword());
			UserInfo dbUserInfo = userInfoService.selectUserInfo(id);
			if (dbUserInfo != null && pw.equals(dbUserInfo.getUserPassword())) {
				result.put("access-token", jwtUtil.createToken("id", userInfo.getUserId()));
				result.put("id", userInfo.getUserId());
				msg = SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		result.put("message", msg);
		return new ResponseEntity<Map<String, Object>>(result, status);
	}
	
	@ApiOperation(
			value = "회원가입",
			notes = "form태그의 정보들을 userInfo객체로 받아 db에 insert, 프로필 사진 추가로 FormData 필요"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userInfo", value = "회원정보를 담은 객체", dataTypeClass = UserInfo.class, required = true)
	})
	@PostMapping("/register")
	public ResponseEntity<String> register(UserInfoWithFile userInfo) throws NoSuchAlgorithmException {
		HttpStatus status = HttpStatus.CONFLICT;
		String msg = FAIL;
		try {
			if (userInfoService.selectUserInfo(userInfo.getUserId()) == null) {
				if (!userInfo.getFile().isEmpty()) {
					File dest = new File(PATH + macOS + userInfo.getUserId() + ".png");
		            try {
		            	userInfo.getFile().transferTo(dest);
		            } catch (IllegalStateException e) {
		               e.printStackTrace();
		            } catch (IOException e) {
		               e.printStackTrace();
		            }
		            userInfo.setFilepath(userInfo.getUserId());
				}
				userInfoService.insertUserInfo(userInfo);
				msg = SUCCESS;
				status = HttpStatus.CREATED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(msg, status);
	}
	@ApiOperation(
			value = "회원조희",
			notes = "id를 통해서 회원정보를 조회. 각종 회원정보가 필요한 기능을 이용할 때 호출 하세용"
					+ "user/info/{아이디}를 실어서 getrequest 하면 됨"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "로그인 할때 보내준 userId", dataTypeClass = String.class, required = true)
	})
	@GetMapping("/info/{userId}")
	public ResponseEntity<UserInfo> getInfo(@PathVariable("userId") String userId){
		return new ResponseEntity<UserInfo>(userInfoService.selectUserInfo(userId), HttpStatus.OK);
	}
	
//	@DeleteMapping("/info/{userId}")
//	public ResponseEntity<String> deleteInfo(@PathVariable("userId") String userId){
//		userInfoService.deleteUserInfo(userId);
//		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//	}
	@ApiOperation(
			value = "정보수정",
			notes = "회원조회와 같이 사용 할 것. 회원정보수정은 form태그의 value로 보내고, 그룹에 가입 할 땐"
					+ "회원정보 요청 한 다음, 해당하는 group number로 값을 변경해서 여기다가 전달,"
					+ "userPoint가 증가 했을때도 같은 방법으로 회원정보 요청하고 userPoint만 바꿔서 전달"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mode", value = "0 : userInfo, 1 : userPoint, 2 : userGroup", dataTypeClass = int.class, required = true),
		@ApiImplicitParam(name = "userInfo", value = "변경할 내용을 수정한 객체(프론트에서 처리)", dataTypeClass = UserInfo.class, required = true)
	})
	@PostMapping("/info/{mode}")
	public ResponseEntity<String> updateInfo(@PathVariable int mode, UserInfoWithFile userInfo){
		if (!userInfo.getFile().isEmpty()) {
			File dest = new File(PATH + macOS + userInfo.getUserId() + ".png");
            try {
            	userInfo.getFile().transferTo(dest);
            } catch (IllegalStateException e) {
               e.printStackTrace();
            } catch (IOException e) {
               e.printStackTrace();
            }
            userInfo.setFilepath(userInfo.getUserId());
		}
		System.out.println(userInfo.toString());
		userInfoService.updateUserInfo(mode, userInfo);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
