import { ReactNode, useEffect, useState } from "react";
import ShortenedUrl from "./shortenedUrl";
import ShortenedUrlComponent from "./ShortenedUrlComponent";
import ShortenUrlForm from "./ShortenUrlForm";

function App(): ReactNode {
  const [shortenedUrl, setShortenedUrl] = useState<ShortenedUrl | null>(null);

  useEffect(() => {
    if (shortenedUrl && (history.state == null || history.state.url != shortenedUrl.url)) {
      history.pushState(shortenedUrl, "", window.location.href)
    }

    const popstateHandler = (event: PopStateEvent) => {
      if (event.state) {
        setShortenedUrl(event.state)
      } else {
        setShortenedUrl(null)
      }
    }

    window.addEventListener("popstate", popstateHandler)

    return () => {
      window.removeEventListener("popstate", popstateHandler)
    }
  }, [shortenedUrl])
 
  return (
    <div className="min-h-screen min-w-screen flex justify-center items-center p-2">
      {!shortenedUrl ? <ShortenUrlForm setShortenedUrl={setShortenedUrl} /> : <ShortenedUrlComponent shortenedUrl={shortenedUrl}/>}
    </div>
  );
}

export default App;
