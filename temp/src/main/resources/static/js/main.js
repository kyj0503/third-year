// main.js

document.addEventListener('DOMContentLoaded', () => {
    const toggleModeButton = document.getElementById('toggle-mode');
    const body = document.body;

    // 현재 모드 상태를 로컬 스토리지에서 가져옴
    const savedMode = localStorage.getItem('theme');
    if (savedMode) {
        body.classList.add(savedMode);
        updateButtonText(savedMode);
    }

    // 모드 전환 버튼 클릭 이벤트
    toggleModeButton.addEventListener('click', () => {
        if (body.classList.contains('dark-mode')) {
            body.classList.remove('dark-mode');
            body.classList.add('light-mode');
            localStorage.setItem('theme', 'light-mode');
            updateButtonText('light-mode');
        } else {
            body.classList.remove('light-mode');
            body.classList.add('dark-mode');
            localStorage.setItem('theme', 'dark-mode');
            updateButtonText('dark-mode');
        }
    });

    // 버튼 텍스트 업데이트 함수
    function updateButtonText(mode) {
        toggleModeButton.textContent = mode === 'dark-mode' ? '라이트 모드' : '다크 모드';
    }
});
