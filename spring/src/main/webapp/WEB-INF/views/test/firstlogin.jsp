<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<body>
<a id="kakao-login-btn" href="javascript:loginWithKakao()">
  <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222"
    alt="카카오 로그인 버튼" />
    <button id="kakaobtn" onclick="kakaobtn();">로그인</button>
    <button id="kakaobtn" onclick="kakaologout();">로그아웃</button>
</a>
</body>
<script>
/*카카오 로그인 서비스  */
Kakao.init("c0e169307572ef60ba8671f2af4eaff4");
console.log(Kakao.isInitialized()); 
//$("#m-kakaobtn").click(e=>{
const kakaobtn=()=>{
	let email,nickname;
		Kakao.Auth.login({
			scope:'profile_nickname,account_email,profile_image'
			success:function(authObj){
				console.log(authObj);
				Kakao.API.request({
					url:'/v2/user/me',
					success:function(res){
						const kakao_account=res.kakao_account;
						console.log(kakao_account);
						email=kakao_account.email;
						nickname=kakao_account.profile.nickname;
						image=kakao_account.profile_image;
						console.log(email,nickname);
							$.ajax({
								url:'<%=request.getContextPath()%>/member/KakaoLoginCheck.do',
								data:{memberEmail:email,memberNickname:nickname},
								dataType:"text",
								success: function(data){
									console.log(data, typeof data);
										if(data=='null'){
									           open("<%=request.getContextPath()%>/views/member/kakaoenroll.jsp?email="+email+"&memberNickname="+nickname
											,"_blank","width=400, height=200, top=300,left=500");
										}else{
											location.assign("<%=request.getContextPath()%>/member/KakaoLogin.do?memberEmail="+email);
										}
										},
								error:(r,m,e)=>{
											console.log(r);
											console.log(m);
										}
							});
					}
				});
			}
		});
}

/*회원탈퇴 로직으로 구현  */
function kakaologout() {
	Kakao.API.request({
    	url: '/v1/user/unlink',
    	success: function(response) {
    		console.log(response);
    		//callback(); //연결끊기(탈퇴)성공시 서버에서 처리할 함수
    		window.location.href="<%=request.getContextPath()%>/views/member/memberLogin.jsp"
    	},
    	fail: function(error) {
    		console.log('탈퇴 미완료')
    		console.log(error);
    	},
	});
};
</script>
</html>