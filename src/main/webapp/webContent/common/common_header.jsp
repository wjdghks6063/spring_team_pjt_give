<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>homepage</title>
<link rel="stylesheet" type="text/css" href="css/reset.css" />
<link rel="stylesheet" type="text/css" href="css/base.css" />
<link rel="stylesheet" type="text/css" href="css/sub.css" />
<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet"> <!--CDN 링크 -->

<link href="css/header.css" rel="stylesheet"> <!-- header css-->
<link href="css/sub-header.css" rel="stylesheet"> <!--서브 header css-->

<!-- 임시 서브 헤더-->
<link href="css/subb.css" rel="stylesheet"> <!--서브 header css-->
<link href="css/subba.css" rel="stylesheet"> <!--서브 header css-->

<link href="css/global.css" rel="stylesheet"> <!--배너 css-->
<link href="css/partner.css" rel="stylesheet"> <!--파트너 css-->

<!-- slick css -->
<link rel="stylesheet" type="text/css" href="css/slick-1.8.1/slick/slick.css"/> 
<link rel="stylesheet" type="text/css" href="css/slick-1.8.1/slick/slick.min.js"/> 
<script type="text/javascript" src="css/slick-1.8.1/slick/slick.min.js"></script>



</head>
<body>
<div id ="containar">
<div id ="warp" class="warp">    
    <header id="header" class="header-header-1">
    <div id ="header-main">
        <div id ="header-top">
            <h1 class="logo-box">
                <a href="" class="logo-img"></a>
            </h1>
            <!-- 헤드 메뉴 내용 -->
            <div class ="main-top-menu">
                <a class="main-text" href="#">기부</a>
                <a class="main-text" href="#">봉사</a>
                <a class="main-text" href="#">재능 기부</a>
                <a class="main-text" href="./mushnotice.html">지역 뉴스
                <a class="main-text" href="#">정기 후원</a>
            </div>
            <!-- 로그인 및 검색버튼-->    
                <a class="login" href ="./mushlogin.html">로그인</a>
                <span class="longbar"></span> <!--로그인 돋보기 사이 | 막대기 -->
                <a  class="search-img" href =""><i class="fas fa-search" style="font-size: 22px;"></i></a>
        </div>
    </div>
    </header>
</div>

	<!--서브 헤더 -->
	<div class="sub-header sub-header-give">
		<h1 class="sub-title"><span class="text">기 부</span></h1>
		<span class="deco-set"><i class="deco ty"></i><i class="deco ty-2"></i></span>

		<div class="lnb">
			<div class="lnb-in">
				<div class="nav">
					<li class="home"><a href="/" class="home-in"><span class="ir i-home">홈</span></a></li>
					<li class="dep" data-dropdown=''><button type="button" data-act='title' data-addtitle="this">큰 메뉴 이름</button>
						
					</li>
					<li class="dep" data-dropdown=''><button type="button" data-act='title' data-addtitle="this">소메뉴</button>
					
					</li>
				</div>
			</div>
		</div>
	</div>

	<!--##### // Visual & LNB #####-->