import React, { useEffect, useState } from "react";
import ROOT from "../../root";
import Logo from "../Homepage/logo.jpg";
import "./Homepage.css";

function LatLon() {
  const [lat, setLat] = useState();
  const [lon, setLon] = useState();
  const [locationDetails, setLocationDetails] = useState(null);

  useEffect(() => {
    navigator.geolocation.getCurrentPosition((position) => {
      setLat(position.coords.latitude);
      setLon(position.coords.longitude);
    });
  }, []); // Empty dependency array to ensure the effect runs only once

  useEffect(() => {
    if (lat && lon) {
      const requestBody = JSON.stringify({
        latitude: lat,
        longitude: lon,
      });
      // https://example.com/api/endpoint
      // Replace "https://example.com" with your actual API root URL
      fetch(`${ROOT}/getCoordinates`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: requestBody,
      })
        .then((response) => response.json())
        .then((data) => setLocationDetails(data))
        .catch((error) =>
          console.error("Error fetching location details:", error)
        );
    }
  }, [lat, lon]); // Effect will run whenever lat or lon changes

  return (
    <>
      {/* <h1>{lat && lon && `${lat}, ${lon}`}</h1>
      {locationDetails && (
        <div>
          <p>City: {locationDetails.city}</p>
          <p>Country: {locationDetails.country_name}</p>
          <p>Country Code: {locationDetails.country_code}</p>
          <p>IP: {locationDetails.IPv4}</p>
        </div>
      )} */}
      <div className="nav">
        <input type="checkbox" id="nav-check" />
        <div className="nav-header">
          <div className="nav-title">JoGeek</div>
        </div>
        <div className="nav-btn">
          <label htmlFor="nav-check">
            <span></span>
            <span></span>
            <span></span>
          </label>
        </div>

        <div className="nav-links">
          <a href="//github.io/jo_geek" target="_blank">
            Project
          </a>
          <a href="http://stackoverflow.com/users/4084003/" target="_blank">
            Organisation
          </a>
          <a
            href="https://in.linkedin.com/in/jonesvinothjoseph"
            target="_blank"
          >
            My Page
          </a>
        </div>
      </div>
    </>
  );
}

export default LatLon;
