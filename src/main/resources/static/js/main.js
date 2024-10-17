





// // Depth1 클릭 시 하위 Depth2를 토글하는 함수
// document.querySelectorAll('.dep1 > a > .dep2 > .flex > .dep2_line').forEach(function(el) {
//     el.addEventListener('click', function(e) {
//         e.preventDefault();  // 링크 클릭 방지
//         var parentLi = this.parentElement;
        
//         // 다른 열려 있는 메뉴를 닫고, 클릭된 메뉴만 열리도록 처리
//         document.querySelectorAll('.dep1 > a ').forEach(function(li) {
//             if (li !== parentLi) {
//                 li.classList.remove('active');
//             }
//         });
        
//         // 현재 클릭한 항목의 하위 메뉴 표시/숨김 토글
//         parentLi.classList.toggle('active');
//     });
// });

// // Depth2 클릭 시 Depth3를 토글하는 함수 (필요시)
// document.querySelectorAll('.dep1 > a > .dep2').forEach(function(el) {
//     el.addEventListener('click', function(e) {
//         e.preventDefault();  // 링크 클릭 방지
//         var parentLi = this.parentElement;
        
//         // Depth3 메뉴 토글
//         parentLi.classList.toggle('active');
//     });
// });

    

function openCategory(){
   
    document.querySelector('.dep2').style.display = 'block'
    
}

function closePopupCategory(){
     document.querySelector('.dep2').style.display = 'none'
}