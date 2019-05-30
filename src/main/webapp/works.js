let gCurrentWorkId;

function onWorksLoad() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onWorksResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/works');
    xhr.send();
}

function onWorksResponse() {
    divEl = document.getElementById('works-content')

    removeElementContent(divEl);
    const worksEl = document.getElementById('works-content')
    const ulEl = document.createElement('ul');
    const works = JSON.parse(this.responseText);
    for (let i = 0; i < works.length; i++) {
        let work = works[i];

        let liEl = document.createElement('li');
        let linkEl = document.createElement('a');

        linkEl.href = "javascript:void(0);";
        linkEl.textContent = work.title;
        linkEl.onclick = function () { onWorkClicked(work.id) };

        liEl.appendChild(linkEl);
        ulEl.appendChild(liEl);

    }
    worksEl.appendChild(ulEl);
}

function onWorkClicked(workId) {
    removeElementContent(document.getElementById('search-result'));
    const loginFormEl = document.forms['search'];
    const regexInputEl = loginFormEl.querySelector('input[name="regex"]');
    regexInputEl.value = "";
    gCurrentWorkId = workId;
    showContents(['profile-content', 'logout-content', 'works-content',
        'work-content', 'search']);
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onWorkReceived);
    xhr.open('GET', 'protected/work?workId=' + workId);
    xhr.send();
}

function onWorkReceived() {
    divEl = document.getElementById('work-content');
    removeElementContent(divEl);

    const work = JSON.parse(this.responseText);
    workEl = document.getElementById('work-content');

    titleEl = document.createElement('h3');
    titleEl.textContent = work.title;

    dateEl = document.createElement('p');
    italicTextEl = document.createElement('i');
    italicTextEl.textContent = work.date.year + '.' + work.date.monthValue + '.' + work.date.dayOfMonth;
    dateEl.appendChild(italicTextEl);

    pEl = document.createElement('p');
    pEl.textContent = work.content;
    pEl.style.whitespace = "pre-line";

    workEl.appendChild(titleEl);
    workEl.appendChild(pEl);
    workEl.appendChild(dateEl);
}

function removeElementContent(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

function onSearchButtonClicked() {
    const loginFormEl = document.forms['search'];

    const regexInputEl = loginFormEl.querySelector('input[name="regex"]');

    const regex = regexInputEl.value;

    const params = new URLSearchParams();
    params.append('regex', regex);
    params.append('workId', gCurrentWorkId);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onSearchResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'protected/work');
    xhr.send(params);
}

function onSearchResponse() {
    const count = this.responseText;
    const searchResult = document.getElementById('search-result');

    searchResult.classList.remove('error');
    searchResult.classList.remove('info');

    if (count != -1) {
        searchResult.classList.add('info');
        searchResult.textContent = 'Found ' + count + ' match(es).';
    }
    else {
        searchResult.classList.add('error');
        searchResult.textContent = 'Invalid regex! Please use ony alpabetical chars!';
    }
}

