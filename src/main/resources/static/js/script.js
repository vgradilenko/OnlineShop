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
        $("#order_body").empty();
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

function getConsumerInOrder() {
    $.get("/consumer/all", function (consumers) {
        $.each(consumers, function (i, consumer) {
            $("#select").append(
                "<option value=" + consumer.id + " >" + consumer.firstName + " " + consumer.lastName + "</option>"
            )
        });
    });
}

function deleteProd(prodId) {
    $.get("/product/delete/" + prodId);
    setTimeout(getAllproducts, 200);
}

function saveProd() {
    var name = $("#name").val();
    var price = $("#price").val();

    var product = {
        name: name,
        price: price
    };

    if (name.length >= 1) {
        $("#name").val("");
    }

    if (price.length >= 1) {
        $("#price").val("");
    }

    $.ajax({
        url: "/product/save",
        type: "post",
        contentType: "application/json",
        data: JSON.stringify(product)
    });
}

var product = [];
var sumProdInOrder = 0;
var productsId = [];
function addProduct(id) {
    $.get("/product/" + id, function (data) {
        var name = data.name;
        var price = data.price;

        product.push(name);
        productsId.push(data);
        sumProdInOrder = sumProdInOrder + price;

        $("#order_products").val(product);
        $("#order_sum").val(sumProdInOrder);
    });
}

function clean() {
    product.length = 0;
    sumProdInOrder = 0;
    $("#order_products").val("");
    $("#order_sum").val("");
}

function getProductFrom(arr) {
    var list = "";
    var summa = 0;
    $.each(arr, function (i, product) {
        list = list + product.name + ", ";
        summa = summa + product.price;
    });
    if (summa === 0) {
        return list;
    } else {
        return list + " Summa: " + summa;
    }
}

function buyAll() {
    var consumerId = document.getElementById("select").value;

    var consumer = {
        id: consumerId
    };

    var order = {
        consumer: consumer,
        products: productsId
    };

    $.ajax({
        url: "/order/save",
        type: "post",
        contentType: "application/json",
        data: JSON.stringify(order)
    });
}

