// 다크 모드 전환 버튼 기능
document.getElementById('toggleDarkMode').addEventListener('click', () => {
    const body = document.body;
    body.classList.toggle('dark-mode');

    // 버튼 텍스트 업데이트
    const button = document.getElementById('toggleDarkMode');
    if (body.classList.contains('dark-mode')) {
        button.textContent = '라이트 모드';
    } else {
        button.textContent = '다크 모드';
    }
});