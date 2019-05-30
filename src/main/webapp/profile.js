function onProfileLoad(user) {
    clearMessages();
    showContents(['profile-content', 'logout-content', 'works-content', 'work-content']);

    const userNameSpanEl = document.getElementById('user-name');

    userNameSpanEl.textContent = user.name;

    onWorksLoad();
}