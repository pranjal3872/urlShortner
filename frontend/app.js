const button =
    document.getElementById("shortenBtn");

button.addEventListener(
    "click",
    async () => {

        const url =
            document.getElementById(
                "urlInput"
            ).value.trim();

        const resultDiv =
            document.getElementById(
                "result"
            );

        const alias =
            document.getElementById(
                "aliasInput"
            ).value.trim();

        // Clear previous result
        resultDiv.innerHTML = "";

        // URL Validation
        try {
            new URL(url);
        } catch {

            resultDiv.innerHTML = `
                <p style="color:red">
                    Please enter a valid URL
                </p>
            `;

            return;
        }

        try {

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
                            url: url,
                            alias: alias
                        })
                    }
                );
                if (!response.ok) {

                    const errorData =
                        await response.json();

                    resultDiv.innerHTML = `
                        <p style="color:red">
                            ${errorData.message}
                        </p>
                    `;

                    return;
                }

            const data =
                await response.json();
                

            const shortUrl =
                `http://localhost:8080/${data.shortCode}`;

            const qrUrl =
                `https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=${encodeURIComponent(shortUrl)}`;

            resultDiv.innerHTML = `
                <p>
                    Short URL:
                    <a href="${shortUrl}" target="_blank">
                        ${shortUrl}
                    </a>
                </p>

                <img
                    src="${qrUrl}"
                    alt="QR Code">

                <br><br>

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

        } catch (error) {

            resultDiv.innerHTML = `
                <p style="color:red">
                    Failed to shorten URL.
                    Please try again.
                </p>
            `;

            console.error(error);
        }
    }
);