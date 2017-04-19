function getAllproducts() {
    $.get("/product/all", function (products) {
        $("#tbody").empty();
        $.each(products, function (i, product) {
            $("#tbody").append(
                $('<tr>').append(
                    "<td>" + product.id + "</td>"
                    + "<td>" + product.name + "</td>"
                    + "<td>" + product.price + "</td>"
                    + "<td>" + product.date + "</td>"
                    + '<td><button onclick="deleteProd(' + product.id + ')">Delete</button></td>'
                    + '<td><button id="buy" onclick="buyProduct(' + product.id + ')">ADD</button></td>'
                    + '<td><a id="count">0</a></td>>'
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

function deleteProd(prodId) {
    $.get("/product/delete/" + prodId);
    setTimeout(getAllproducts, 200);
}

function saveProd(name, price) {
    $.post("/product/" + name + "/" + price);
    setTimeout(getAllproducts, 200);
}

function buyProduct(id) {
    var count = 0;
    $("#buy").onclick = function () {
        count++;
    };
    var product = $.get("/product/" + id);
    console.log(product + " " + count);
}


