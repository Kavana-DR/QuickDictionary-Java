function searchMeaning() {
    const word = document.getElementById("wordInput").value.trim();
    const resultBox = document.getElementById("result");

    if (!word) {
        resultBox.innerHTML = "<p>⚠️ Please enter a word.</p>";
        return;
    }

    resultBox.innerHTML = "<p>⏳ Searching...</p>";

    fetch(`https://api.dictionaryapi.dev/api/v2/entries/en/${word}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Word not found");
            }
            return response.json();
        })
        .then(data => {
            let definition = data[0].meanings[0].definitions[0].definition;
            let example = data[0].meanings[0].definitions[0].example || "No example available.";

            resultBox.innerHTML = `
                <h2>📗 ${word}</h2>
                <p><strong>Meaning:</strong> ${definition}</p>
                <p><strong>Example:</strong> ${example}</p>
            `;
        })
        .catch(() => {
            resultBox.innerHTML = "<p>❌ Word not found. Try another word.</p>";
        });
}
