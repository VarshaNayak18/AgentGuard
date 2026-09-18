import os
from groq import Groq

client = Groq(
    api_key=os.environ.get("GROQ_API_KEY")
)

response = client.chat.completions.create(
    model="openai/gpt-oss-20b",
    messages=[
        {
            "role": "system",
            "content": "You are a cybersecurity analyst."
        },
        {
            "role": "user",
            "content": "Explain why executing a remote shell script can be dangerous."
        }
    ],
)

print(response.choices[0].message.content)