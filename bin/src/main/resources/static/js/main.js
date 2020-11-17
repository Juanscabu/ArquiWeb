document.addEventListener("DOMContentLoaded", init);
let baseURL = "http://localhost:8080";

function init() {
    "use strict";
    let data = null;
    getData();
    async function getData() {
        let r = await fetch(baseURL + "/product/");
        let json = await r.json();
        data = await json;
        showData(data);
    }
    async function showData(data) {
        $('#myTable').DataTable({
            data: data,
            columns: [
                { data: 'idProduct' },
                { data: 'name' },
                { data: 'price' },
                { data: 'details' }
            ]
        });
    }

    // document.querySelector("#submitProduct").addEventListener("click", addProduct)
    // async function addProduct() {
    //     event.preventDefault();
    //     let name = document.querySelector("#name").value;
    //     let price = document.querySelector("#price").value;
    //     let details = document.querySelector("#details").value;
    //     let data = {
    //         "name": name,
    //         "price": price,
    //         "details": details
    //     }

    //     let r = await fetch(baseURL + "/product/", {
    //         method: 'POST', // or 'PUT'
    //         body: JSON.stringify(data), // data can be `string` or {object}!
    //         headers: {
    //             'Content-Type': 'application/json'
    //         }
    //     })
    //     let json = await r.json();
    //     let data1 = await json;
    //     // showData(data1);
    //     console.log(data1);
    //     getData();
    // }
}