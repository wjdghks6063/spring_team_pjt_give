<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>

    <!--페이지 css-->
    <link href="css/mypage-menu.css" rel="stylesheet">
    <link href="css/my-page-regular payment.css" rel="stylesheet">





    <div id="containar">
        <!--script 밑에 body와 </div> 있음-->

        <!-- 마이 페이지 메뉴-->
        <div class="my-page-box">
            <div class="my-list-left">
                <div class="my-profile">
                <div class="my_profile_nickname">
                    <span class="ellipsis" id="nickNameArea">${session_name}</span>
                </div>
                <div class="my_profile_id" id="maskingIdArea">${session_id}</div>
            </div>
                <div id="lnb" class="my_lnb" role="menu">
                    <!-- aria-current="#" 여부에 따라 색상 불 들어옴-->
                    <a href="Mypage_home?t_id=${session_id}" role="menuitem" class="my_lnb_item" id="lnb_my_home" aria-current="false">MY홈</a>
                    <a href="Mypage_news?t_id=${session_id}" role="menuitem" class="my_lnb_item" id="lnb_my_notification" aria-current="false">내 소식</a>
                    <a href="Mypage_activity?t_id=${session_id}" role="menuitem" class="my_lnb_item" id="lnb_my_activity" aria-current="false">활동내역</a>
                    <a href="Mypage_regular_payment?t_id=${session_id}" role="menuitem" id="my_lnb_item" class="my_lnb_item" aria-current="true">정기결제관리</a>
                    <a href="Mypage_year?t_id=${session_id}" role="menuitem" class="my_lnb_item" id="lnb_my_tax" aria-current="false">연말정산</a>
                </div>
            </div>


            <!-- 내용 창 -->
            <div role="main" id="content" class="my_content">
                <div class="my_regular_menu" role="menubar">
                    <a href="/my/regularPayment?type=donation" class="my_regular_menu_item" id="tab_regular_donation" data-name="donation" role="menuitem" aria-current="true">정기기부</a> 
                    <a href="/my/regularPayment?type=deposit" class="my_regular_menu_item" id="tab_regular_deposit" data-name="deposit" role="menuitem" aria-current="false">정기저금</a>
                </div>
                <div class="my_regular_wrap">
                    <h4 class="my_title" id="regularPaymentTitle">진행 중 정기기부</h4>
                    <!-- 기부 내역이 있을 때만 display block 으로 출력시킴-->
                    <div class="my_activity_total" aria-hidden="ture">총 <span class="point" id="totalCount"></span>건
                    </div>
                    <ul class="my_activity_list" id="regularPaymentList" aria-hidden="true"></ul>
                    <button type="button" class="btn_horizontal_more" style="display:none">더보기</button>
                    <!--숨겨져 있다가 후원 내용이 몇개 이상 있을 시 block 처리됨-->
                    <!-- 기부 내용이 있을 시 종료된 정기목록 링크 창 생성 aria-hidden="false" 로 처리-->
                    <a href="#" class="my_regular_link" id="oppositionMoveButton" aria-hidden="true" data-status="end"
                        data-type="donation">종료된 정기기부 목록</a>
                    <div class="none_message" aria-hidden="false">
                        <p class="none_message_inner" id="nothingArea">진행 중인 정기기부가 없습니다.<br>
                            <a href="/donation/DonateHomeMain" class="link2">정기기부 보러가기</a><br>
                            <a href="/my/regularPayment?type=donation&amp;status=end" class="link_small">종료된 정기기부 목록</a><br>
                        </p>
                    </div>
                </div>
            </div>

            <!-- 결제 관리창  관리 버튼 눌르면 class="dim" 와 id="markup_popup" 의 aria-hidden 값이 flase 처리 되어야 한다.-->
            <div class="dim" aria-hidden="true"></div>
            <div id="markup_popup" aria-hidden="true" class="ly_wrapper">
                <div class="ly_npay_change">
                    <h1 class="pop_tit"><span class="ico_npay"></span><span class="tit_tx">결제수단/금액변경</span></h1>
                    <p class="pop_txt">이번 달 정기결제는 기존 설정으로 진행되며,<br>변경사항은 다음 정기결제에 반영됩니다.</p>
                    <form class="info_form">
                        <ul class="change_option">
                            <li class="option_item"><input type="radio" id="changeOpt1" class="input_radio"
                                    name="changeOpt" checked="checked"> <label for="changeOpt1" class="label"><span
                                        class="rdo"></span> <span class="txt">결제 수단만 변경하기</span></label></li>
                            <li class="option_item"><input type="radio" id="changeOpt2" class="input_radio"
                                    name="changeOpt"> <label for="changeOpt2" class="label"><span class="rdo"></span>
                                    <span class="txt">결제 금액 변경하기<span class="txt_desc">(결제수단 함께 변경
                                            가능)</span></span></label>
                                <div class="detail">
                                    <div class="detail_input">매달<span class="input_modify"><input id="changeAmount"
                                                class="input" name="amount" title="금액입력"> <span id="paymentUnitAlert"
                                                class="alert_modify">100원 단위로만 가능합니다.</span> </span>원으로 변경</div>
                                    <ul class="detail_list">
                                        <!-- 1000원 이하 입력시 붉은 메세지 창 생성되야 함-->
                                        <li class="detail_item">
                                            <button type="button" class="button_modify type_direct amount_input" aria-pressed="true">직접입력</button>
                                        </li>
                                        <li class="detail_item">
                                            <button type="button" class="button_modify amount_registered" data-amount="5,000">5천원</button>
                                        </li>
                                        <li class="detail_item">
                                            <button type="button" class="button_modify amount_registered" data-amount="10,000">1만원</button>
                                        </li>
                                        <li class="detail_item">
                                            <button type="button" class="button_modify amount_registered" data-amount="30,000">3만원</button>
                                        </li>
                                        <li class="detail_item">
                                            <button type="button" class="button_modify amount_registered" data-amount="50,000">5만원</button>
                                        </li>
                                        <li class="detail_item">
                                            <button type="button" class="button_modify amount_registered" data-amount="100,000">10만원</button>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </form>

                    <div class="buttons">
                        <a href="#" class="button_confirm" role="button">확인</a> 
                        <a href="#" class="button_cancel" role="button">닫기</a>
                    </div>
                </div>
            </div>
        </div>
        <!--my-page-box-->



        <script>
            /*메인 header 따라 오기 */
            let header = document.querySelector(".header-header-1");
            let headerHeight = header.offsetHeight;

            window.onscroll = function () {
                let windowTop = window.scrollY;
                if (windowTop >= headerHeight) {
                    header.classList.add("is-scroll");
                } else {
                    header.classList.remove("is-scroll");
                }
            };
            // 

            var kkeys = [],
                konami = "38,38,40,40,37,39,37,39,66,65";
            $(document).keydown(function (e) {
                kkeys.push(e.keyCode);
                if (kkeys.toString().indexOf(konami) >= 0) {
                    kkeys = [];
                    alert('안녕하세요');
                }
            });
        </script>

    </div>
</body>
<!--footer -->
<footer class="footer-box">
    <div class="footer-top">
        <dl class="footer-top-box">
            <dt class="footer-top-notice"><a href="">공지사항</a></dt>
            <dd class="foorer-top-notice-text">
                <a href="">회원 가입시 개인정보 관리 내역에 대해 안내해드립니다.<i class="icon-n"><i class="fab fa-envira"></i></i></a>
            </dd>
        </dl>
        <div class="service-info-box" aria-hidden="false">
            <dl class="service-info-first-line">
                <dt class="service-info-first-line-title">해피빈 안내</dt>
                <dd class="service-info-first-line-item"><a href="">해피빈 소개</a></dd>
                <dd class="service-info-first-line-item"><a href="">콩받기 안내</a></dd>
                <dd class="service-info-first-line-item"><a href="">해피빈 현황</a></dd>
                <dd class="service-info-first-line-item"><a href="">콩구폰 입력</a></dd>
            </dl>
            <dl class="service-info-first-line">
                <dt class="service-info-first-line-title">제휴∙단체안내</dt>
                <dd class="service-info-first-line-item"><a href="">해피로그 가입</a></dd>
                <dd class="service-info-first-line-item"><a href="">기업제휴 문의</a></dd>
                <dd class="service-info-first-line-item"><a href="">펀딩 개설 신청</a></dd>
            </dl>
        </div>
    </div>

    <div class="footer-info-box">
        <div class="footer-info-inner">
            <ul class="footer-info-list">
                <li class="footer-info-item"><a href="" target="해피빈 이용약관">해피빈 이용약관</a></li>
                <li class="footer-info-item"><a href="" target="개인정보처리방침"><strong>개인정보처리방침</strong></a></li>
                <li class="footer-info-item"><a href="" target="책임의 한계와 법적고지">책임의 한계와 법적고지</a></li>
                <li class="footer-info-item"><a href="" target="고객센터">고객센터</a></li>
                <li class="footer-info-item"><a href="" target="공익제보(행정안전부)">공익제보(행정안전부)</a></li>
            </ul><br>
            <!--ul 끼리 붙어 있어 줄바꿈이 생기지 않아서 넣어줌-->
            <ul class="footer-info-list">
                <li class="footer-info-item">제단법인 굿아이디어</li>
                <li class="footer-info-item">대표 : 최정우</li>
                <li class="footer-info-item">사업자 등록번호 :199-40-91928</li>
                <li class="footer-info-item">통신판매업신고 : 2015-대전충남-1994호</li>
            </ul><br>
            <address class="footer-info-address">대전 충남 대덕대로 150 11층 (갈마동,창원빌딩) 대표전화 : 1588-8282</address>
            <small class="footer-info_notice">
                재능 기부 및 직접기부의 진행과 리워드 제공의 책임은 해당 프로젝트의 개설자에게 있습니다. 굿아이디어는 해당 프로젝트의 당사자가 아니며, 리워드 제공에 대한 책임을 지지 않습니다.
            </small>
            <div class="footer-info-copyright">
                <a href="" class="footer-info-logo">
                    <img src="img/company.png" class="company-logo" alt="LOGO">
                </a> <br>
                <span class="Copyright">Copyright © G&I Corp. All Rights Reserved.</span>
            </div>
        </div>
    </div>

</footer>

</div>
</div>
</div>

</html>