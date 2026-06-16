async function loadAnalytics() {

    const response =
        await fetch(
            "http://localhost:8080/api/analytics"
        );

    const urls =
        await response.json();

    const tbody =
        document.querySelector(
            "#analyticsTable tbody"
        );

    urls.forEach(url => {

        tbody.innerHTML += `
            <tr>
                <td>${url.shortCode}</td>
                <td>${url.originalUrl}</td>
                <td>${url.clickCount}</td>
            </tr>
        `;
    });
}

loadAnalytics();