/**
 * 
 */
function qna_check() {
	var subject = document.getElementById("subject");
	var content = document.getElementById("content");

	if (subject.value == "") {
		alert("제목을 입력하세요.");
		subject.focus();
		return false;
	} else if (content.value == "") {
		alert("내용을 입력해주세요.");
		content.focus();
		return false;
	} else {
		var theForm = document.getElementById("form");
		theForm.action = "qna_write";
		theForm.submit();
	}

}