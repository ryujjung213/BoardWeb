/**
 * 장바구니 관련 자바스크립트 함수 
 */

// 장바구니 담기 요청 전송

function go_cart() {
	var qty = document.getElementById("quantity");
	
	if (qty.value =="") {
		alert("수량을 입력해 주세요.");
		qty.focus();
		
		return false;
	} else if (qty.value > 30) {
		alert("수량이 너무 큽니다.");
		qty.focus();
		
		return false;
	} else {
		var theForm = document.getElementById("theform")
		theForm.action = "cart_insert";
		theForm.submit();
	}
}


function go_cart_delete() {
	// input 태그의 엘리먼트 중 name 속성이 cseq인 것들 중에서
	// check된 항목을 변수에 저장
	const query ="input[name='cseq']:checked";
	
	// 체크한 항목의 갯수
	var len =  document.querySelectorAll(query).length;
	
	if(len == 0) {
		alert("삭제할 항목을 선택해 주세요!");
	} else {
		var theForm = document.getElementById("theform");
		
		theForm.action ="cart_delete";
		theForm.submit();
	}
}

//  장바구니 내역 주문처리 요청

function go_order_insert() {
	var theForm = document.getElementById("theform");
	
	theForm.action ="order_insert";
	theForm.submit();
}