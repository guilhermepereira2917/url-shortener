import { AxiosError, AxiosResponse } from "axios";
import { FormEvent, ReactNode, useState } from "react";
import api from "./api";
import ShortenedUrl from "./shortenedUrl";

interface ShortenUrlFormProps {
  setShortenedUrl: (url: ShortenedUrl) => void
}

export default function ShortenUrlForm({ setShortenedUrl }: ShortenUrlFormProps): ReactNode {
  const [url, setUrl] = useState("")
  const [error, setError] = useState("")
  const [loading, setLoading] = useState(false)

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const value = event.target.value;
    setUrl(value);

    if (!value.trim() && error === "") {
      setError("URL cannot be empty")
    } else if (value.trim() && error !== "") {
      setError("")
    }
  };

  const handleSubmit = (event: FormEvent) => {
    event.preventDefault()

    if (!url.trim()) {
      setError("URL cannot be empty")
      return
    }

    setLoading(true)

    api.post(`/shorten-url`, {
        url: url,    
      })
      .then((response: AxiosResponse<ShortenedUrl>) => {        
        setLoading(false)
        setShortenedUrl(response.data)
      })
      .catch((error: AxiosError) => {
        setLoading(false)        

        if (error.status == 400) {
          setError("Invalid URL, please make sure to submit a valid one")
        } else {
          setError("Unable to shorten the URL :(, try again in a few moments")      
        }
      })
  };

  return (
    <form onSubmit={handleSubmit} className="max-w-xl w-lvw flex flex-col gap-1">
      <h1>Shorten your URLs with one click</h1>
      <input
        type="text"
        value={url}
        onChange={handleChange}
        placeholder="Example: https://www.google.com"
        className={`outline w-full p-2 rounded ${error ? "outline-red-400 text-red-400" : ""}`}
      />

      {error && <span className="text-red-600">{error}</span>}

      <button type="submit" className={`bg-black text-white font-bold p-2 rounded ${loading ? "cursor-not-allowed opacity-80" : "cursor-pointer"}`} disabled={loading}>
        {loading ? "Shortening URL..." : "Shorten URL"}
      </button>
    </form>
  )
}