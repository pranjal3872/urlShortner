const button =
    document.getElementById("shortenBtn");

button.addEventListener(
    "click",
    async () => {

        const url =
            document.getElementById(
                "urlInput"
            ).value;

        const response =
            await fetch(
                "http://localhost:8080/api/shorten",
                {
                    method: "POST",
                    headers: {
                        "Content-Type":
                            "application/json"
                    },
                    body: JSON.stringify({
                        url: url
                    })
                }
            );

        const data =
            await response.json();

        const shortUrl =
            `http://localhost:8080/${data.shortCode}`;

        document.getElementById("result").innerHTML = `
            <p>
                Short URL:
                <a href="${shortUrl}" target="_blank">
                    ${shortUrl}
                </a>
            </p>

            <button id="copyBtn">
                Copy URL
            </button>
        `;

        document
        .getElementById("copyBtn")
        .addEventListener(
            "click",
            () => {

                navigator.clipboard.writeText(
                    shortUrl
                );

                document.getElementById(
                    "copyBtn"
                ).innerText = "Copied!";

                setTimeout(() => {

                    document.getElementById(
                        "copyBtn"
                    ).innerText = "Copy URL";

                }, 2000);
            }
        );
    }
);