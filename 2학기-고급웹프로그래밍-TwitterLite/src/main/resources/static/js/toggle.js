function toggleEditMode(postId) {
    const postElement = document.getElementById(`post-${postId}`);
    const contentElement = postElement.querySelector(".card-text");
    const editForm = postElement.querySelector(".edit-form");

    // 토글 로직
    if (editForm.style.display === "none") {
        editForm.style.display = "block";
        contentElement.style.display = "none";
    } else {
        editForm.style.display = "none";
        contentElement.style.display = "block";
    }
}

function toggleEditComment(commentId) {
    const editForm = document.querySelector(`form[action="/comments/${commentId}/edit"]`);
    const commentContent = editForm.previousElementSibling.querySelector('.comment-content');

    // 토글 수정 폼
    if (editForm.classList.contains('d-none')) {
        editForm.querySelector('textarea').value = commentContent.textContent.trim();
        editForm.classList.remove('d-none');
    } else {
        editForm.classList.add('d-none');
    }
}

function toggleLike(postId, button) {
    fetch(`/posts/${postId}/like`, {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
    })
        .then((response) => response.json())
        .then((data) => {
            if (data.success) {
                if (data.isLiked) {
                    button.classList.remove('btn-outline-primary');
                    button.classList.add('btn-primary');
                } else {
                    button.classList.remove('btn-primary');
                    button.classList.add('btn-outline-primary');
                }
                const likeCountElement = button.nextElementSibling;
                likeCountElement.textContent = `좋아요 ${data.likeCount}개`;
            } else {
                alert(data.message || "오류가 발생했습니다.");
            }
        })
        .catch((error) => {
            console.error("좋아요 요청 중 오류 발생:", error);
            alert("좋아요 요청 중 오류가 발생했습니다.");
        });
}