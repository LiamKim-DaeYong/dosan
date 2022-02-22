function addressApi(zipCodeId, addressId, detailId) {
    new daum.Postcode({
        oncomplete: function(data) { //선택시 입력값 세팅
            document.getElementById(zipCodeId).value = data.zonecode; // 주소 넣기
            document.getElementById(addressId).value = data.address; // 주소 넣기
            document.getElementById(detailId).focus();
        }
    }).open();
}
