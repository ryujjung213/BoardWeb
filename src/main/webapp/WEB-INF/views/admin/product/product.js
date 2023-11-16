/**
 * 상품 관련 관리자 함수
 */

/*
 * 상품명으로 상품 목록 조회요청
 */
function go_search() {
	var theForm = document.getElementById("prod_form");

	theForm.action = "admin_product_list";
	theForm.submit();
}
/*
 * 상품일렬번호로 상품 상세정보 요청
 */
function go_detail(pseq) {
	var theForm = document.getElementById("prod_form");

	theForm.action = "admin_product_detail?pseq="+ pseq;
	theForm.submit();
}
/*
 * 상품 수정 화면 표시 및 데이터 요청
 */
function go_mod() {

	   var theForm = document.getElementById("detail_form");

	   theForm.action = "admin_product_update_form";
	   theForm.submit();
	}


/*
 *  상품 등록 화면 표시 요청
 */
function go_wrt() {
	
	   var theForm = document.getElementById("prod_form");

	   theForm.action = "admin_product_write_form";
	   theForm.submit();
	}



/*
 * price3(순익) 필드 입력
 */

function go_ab(){
	   //판매가
	   var price2 = document.getElementById("price2").value;
	   //원가
	   var price1 = document.getElementById("price1").value;
	   var price3 = price2 - price1;
	   
	   document.getElementById("price3").value = price3;
	}

/*
 * 상품 등록 요청
 */
function go_save() {

	   var kind = document.getElementById("kind");
	   var name = document.getElementById("name");
	   var price1 = document.getElementById("price1");
	   var price2 = document.getElementById("price2");
	   var price3 = document.getElementById("price3");
	   var content = document.getElementById("content");

	   if (document.getElementById("kind").value == "") {
	      alert("상품 종류를 입력하세요.");
	      document.getElementById("kind").focus();
	      return false;
	   } else if (document.getElementById("name").value == "") {
	      alert("상품 이름을 입력해주세요");
	      document.getElementById("name").focus();
	      return false;
	   } else if (document.getElementById("price1").value == "") {
	      alert("상품의 원가를 입력해주세요");
	      document.getElementById("price1").focus();
	      return false;
	   } else if (document.getElementById("price2").value == "") {
	      alert("상품의 판매가를 입력해주세요");
	      document.getElementById("price2").focus();
	      return false;
	   } else if (document.getElementById("price3").value == "") {
	      alert("순익값을 입력하세요.");
	      document.getElementById("price3").focus();
	      return false;
	   } else if (document.getElementById("content").value == "") {
	      alert("상품 설명을 입력해주세요.");
	      document.getElementById("content").focus();
	      return false;
	   } else {
	      var theForm = document.getElementById("write_form");
	      theForm.action = "admin_product_write";
	      theForm.enctype = "multipart/form-data";
	      theForm.submit();
	   }

	}
/*
 *  상품 리스트 화면에서 "전체보기" 메뉴 
 */

function go_total() {
	var theForm = document.getElementById("prod_form");
	theForm.action="admin_product_list";
	theForm.submit();
}
/*
 * 상품 상세보기 화면에서 "목록" 버튼
 */

function go_list() {
	var theForm = document.getElementById("detail_form");
	theForm.action="admin_product_list";
	theForm.submit();
}

function NumFormat(t) {
	   var s = t.value;
	   s = s.replace(/\D/g, ''); // 숫자가 아닌 경우 제거

	   t.value = s;
	   return t;
	}

/*
 * 상품 정보 수정 요청
 */
function go_mod_save() {
	if (document.getElementById("kind").value == "") {
	      alert("상품 종류를 입력하세요.");
	      document.getElementById("kind").focus();
	      return false;
	   } else if (document.getElementById("name").value == "") {
	      alert("상품 이름을 입력해주세요");
	      document.getElementById("name").focus();
	      return false;
	   } else if (document.getElementById("price1").value == "") {
	      alert("상품의 원가를 입력해주세요");
	      document.getElementById("price1").focus();
	      return false;
	   } else if (document.getElementById("price2").value == "") {
	      alert("상품의 판매가를 입력해주세요");
	      document.getElementById("price2").focus();
	      return false;
	   } else if (document.getElementById("price3").value == "") {
	      alert("순익값을 입력하세요.");
	      document.getElementById("price3").focus();
	      return false;
	   } else if (document.getElementById("content").value == "") {
	      alert("상품 설명을 입력해주세요.");
	      document.getElementById("content").focus();
	      return false;
	   } else {
		   var theForm = document.getElementById("update_form");
		   theForm.enctype ="multipart/form-data";
		   theForm.action ="admin_product_update";
		   
		   theForm.submit();
	   }
}