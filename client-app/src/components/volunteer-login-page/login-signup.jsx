import React, { useState } from "react";
import "./login-signup.css";
import ROOT from "../../root";

const Login = () => {
  const [selectedUserType, setSelectedUserType] = useState("volunteer");
  const handleUserTypeChange = (event) => {
    setSelectedUserType(event.target.value);
  };
  const toggleForm = () => {
    const container = document.querySelector(".container");
    container.classList.toggle("active");
  };
  const [signInFormData, setSignInFormData] = useState({
    username: "",
    password: "",
  });

  const [signUpFormData, setSignUpFormData] = useState({
    name: "",
    username: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const [formType, setFormType] = useState("signin");

  const handleSignInSubmit = (event) => {
    event.preventDefault();

    const signInBody = JSON.stringify({
      email: signInFormData.username,
      password: signInFormData.password,
      userType: "volunteer/organisation",
    });

    fetch(`${ROOT}/signin`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: signInBody,
    })
      .then((response) => response.json())
      .then((data) => {
        // Handle successful sign-in response
        console.log("Sign-in successful", data);
      })
      .catch((error) => {
        // Handle sign-in error
        console.error("Sign-in error:", error);
      });
  };

  const handleSignUpSubmit = (event) => {
    event.preventDefault();

    const signUpBody = JSON.stringify({
      name: signUpFormData.name,
      username: signUpFormData.username,
      email: signUpFormData.email,
      password: signUpFormData.password,
      userType: "volunteer/organisation",
    });

    fetch("signup_endpoint_url", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: signUpBody,
    })
      .then((response) => response.json())
      .then((data) => {
        // Handle successful sign-up response
        console.log("Sign-up successful", data);
      })
      .catch((error) => {
        // Handle sign-up error
        console.error("Sign-up error:", error);
      });
  };

  return (
    <section>
      <div className="container">
        <div
          className={`user signinBx ${formType === "signin" ? "active" : ""}`}
        >
          <div className="imgBx">
            <img
              src="https://raw.githubusercontent.com/WoojinFive/CSS_Playground/master/Responsive%20Login%20and%20Registration%20Form/img1.jpg"
              alt=""
            />
          </div>
          <div className="formBx">
            <form onSubmit={handleSignInSubmit}>
              <h2>Sign In</h2>
              <input
                type="text"
                placeholder="Username"
                value={signInFormData.username}
                onChange={(e) =>
                  setSignInFormData({
                    ...signInFormData,
                    username: e.target.value,
                  })
                }
              />
              <input
                type="password"
                placeholder="Password"
                value={signInFormData.password}
                onChange={(e) =>
                  setSignInFormData({
                    ...signInFormData,
                    password: e.target.value,
                  })
                }
              />
              <input type="submit" value="Login" />
              <p className="signup">
                Don't have an account ?
                <button type="button" onClick={() => toggleForm()}>
                  Sign Up.
                </button>
              </p>
            </form>
          </div>
        </div>
        <div
          className={`user signupBx ${formType === "signup" ? "active" : ""}`}
        >
          <div className="formBx">
            <form onSubmit={handleSignUpSubmit}>
              <h2>Create an account</h2>
              <div>
                <label>
                  <h5>Volunteer</h5>
                  <input
                    type="radio"
                    value="volunteer"
                    checked={selectedUserType === "volunteer"}
                    onChange={handleUserTypeChange}
                    className="radio"
                  />
                </label>
                <label>
                  <h5> Organisation </h5>
                  <input
                    type="radio"
                    value="organisation"
                    checked={selectedUserType === "organisation"}
                    onChange={handleUserTypeChange}
                  />
                </label>
              </div>

              <input
                type="text"
                placeholder="Name"
                value={signUpFormData.name}
                onChange={(e) =>
                  setSignUpFormData({ ...signUpFormData, name: e.target.value })
                }
              />
              <input
                type="text"
                placeholder="Username"
                value={signUpFormData.username}
                onChange={(e) =>
                  setSignUpFormData({
                    ...signUpFormData,
                    username: e.target.value,
                  })
                }
              />
              <input
                type="email"
                placeholder="Email Address"
                value={signUpFormData.email}
                onChange={(e) =>
                  setSignUpFormData({
                    ...signUpFormData,
                    email: e.target.value,
                  })
                }
              />
              <input
                type="password"
                placeholder="Create Password"
                value={signUpFormData.password}
                onChange={(e) =>
                  setSignUpFormData({
                    ...signUpFormData,
                    password: e.target.value,
                  })
                }
              />
              <input
                type="password"
                placeholder="Confirm Password"
                value={signUpFormData.confirmPassword}
                onChange={(e) =>
                  setSignUpFormData({
                    ...signUpFormData,
                    confirmPassword: e.target.value,
                  })
                }
              />
              <input type="submit" value="Sign Up" />
              <p className="signup">
                Already have an account ?
                <button type="button" onClick={() => toggleForm()}>
                  Sign in.
                </button>
              </p>
            </form>
          </div>
          <div className="imgBx">
            <img
              src="https://raw.githubusercontent.com/WoojinFive/CSS_Playground/master/Responsive%20Login%20and%20Registration%20Form/img2.jpg"
              alt=""
            />
          </div>
        </div>
      </div>
    </section>
  );
};

export default Login;
