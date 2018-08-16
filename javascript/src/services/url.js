//const url = "http://localhost:8080";
//const url = "http://localhost:8080/portal/home";
const url = "http://localhost:8080/api/me";

export function getHome(){
    return fetch(url);
}