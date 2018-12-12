function makeCell(content, i, j) {
    return "<div onclick=cellClicked(" + i + "," + j + ") style='" +
                " float: left; "+
                " height: 50px; "+
                " width: 50px; "+
                " font-size: 35px; "+
                " text-align: center; "+
                " line-height: 50px;"+
                " border: 1px solid black;"+
            "'>" + content + "</div>";
}

function cellClicked(i, j) {
    console.log("Clicked on", i, j);
    $.post("/api/move", JSON.stringify({ "i": i, "j": j})).done(boardData => {
        $("#board-container").empty();
        console.log("recd", boardData)
        draw($.parseJSON(boardData));
    });
}

function draw(boardData) {
    const cells = boardData.map((row, i) => row.map((value, j) => makeCell(value, i, j)));
    const content = cells.map(row => row.join(" ")).join("<div style='clear: both'/>")
    $("#board-container").append(content);
}

$.getJSON("/api/board", draw);



