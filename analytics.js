async function loadAnalytics() {

    const response =
        await fetch(
            "https://urlshort-jdms.onrender.com/api/analytics"
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