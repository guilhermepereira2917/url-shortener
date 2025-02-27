import ShortenedUrl from "./shortenedUrl";

interface ShortenedUrlProps {
  shortenedUrl: ShortenedUrl,
}

export default function ShortenedUrlComponent({ shortenedUrl }: ShortenedUrlProps) {
  const url = shortenedUrl.url;
  const formattedExpiresAt = new Date(shortenedUrl.expiresAt).toLocaleString();

  const handleCopyClick = () => {
    navigator.permissions.query({ name: "clipboard-write" as PermissionName }).then((result) => {
      if (result.state === "granted" || result.state === "prompt") {
        navigator.clipboard.writeText(url);
      }
    });    
  }

  const handleBackClick = () => {
    window.history.back();
  }

  return (
    <div className="text-center">
      <h2 className="text-green-500">URL Shortened Successfully!</h2>
      <div className="flex gap-2">
        <p>Your shortened URL: <a href={url} target="_blank" className="text-blue-500 underline">{url}</a></p>      
        <button onClick={handleCopyClick} className="bg-[url(/assets/copy-icon.svg)] bg-contain bg-no-repeat bg-center p-3 outline rounded cursor-pointer active:bg-gray-200"/>
      </div>
      <p className="text-gray-500 text-sm">Expires on: {formattedExpiresAt}</p>
      <button onClick={handleBackClick} className="bg-black text-white font-bold p-2 w-full cursor-pointer rounded">Shorten another link</button>
    </div>
  );
}
