function getAllproducts() {
    $.get("/product/all", function (products) {
        $("#tbody").empty();
        $.each(products, function (i, product) {
            $("#tbody").append(
                $('<tr>').append(
                    "<td>" + product.id + "</td>"
                    + "<td>" + product.name + "</td>"
                    + "<td>" + product.price + "</td>"
                    + '<td><button onclick="deleteProd(' + product.id + ')">Delete</button></td>'
                    + '<td><button id="addbtn" onclick="addProduct(' + product.id + ')">ADD</button></td>'
                ).append('</tr>')
            )
        });
    });
}

function getAllConsumers() {
    $.get("/consumer/all", function (consumers) {
        $.each(consumers, function (i, consumer) {
            $("#con_body").append(
                $("<tr>").append(
                    "<td>" + consumer.id + "</td>"
                    + "<td>" + consumer.firstName + "</td>"
                    + "<td>" + consumer.lastName + "</td>"
                    + "<td>" + consumer.money + "</td>"
                    + "<td>" + "0" + consumer.telephone + "</td>"
                )
            ).append("</tr>")
        });
    });
}

function getOrders() {
    $.get("/order/all", function (orders) {
        $.each(orders, function (i, order) {
            $("#order_body").append(
                $("<tr>").append(
                    "<td>" + order.id + "</td>"
                    + "<td>" + order.consumer.firstName + "</td>"
                    + "<td>" + getProductFrom(order.products) + "</td>"
                )
            ).append("</tr>")
        });
    });
}

function deleteProd(prodId) {
    $.get("/product/delete/" + prodId);
    setTimeout(getAllproducts, 200);
}

function saveProd(name, price) {
    $.post("/product/" + name + "/" + price);
    setTimeout(getAllproducts, 200);
}

var arr = [];
function addProduct(id) {
    arr.push(id);
    console.log(arr);
}
function getProductFrom(products) {
    var result = "";
    var fromJson = JSON.stringify(products);
    $.each(fromJson, function (i, product) {
        result = result + product.name + " ";
    });
    return result;
}


