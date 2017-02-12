$(document).ready(function() {
    $("#egp-count-slider").slider({
        range: false,
        min: 13,
        max: 120,
        values: [24],
        slide: function(event, ui) {
            console.log(ui.values[0]);
            $('#egp-count').html(ui.values[0]);
            rounds = ui.values[0];
        }
    });

    $("#egp-cost-slider").slider({
        range: false,
        min: 13,
        max: 120,
        values: [24],
        slide: function(event, ui) {
            console.log(ui.values[0]);
            $('#egp-cost').html(ui.values[0] + "$");
            rounds = ui.values[0];
        }
    });

    $("#esm-count-slider").slider({
        range: false,
        min: 13,
        max: 120,
        values: [24],
        slide: function(event, ui) {
            console.log(ui.values[0]);
            $('#esm-count').html(ui.values[0]);
            rounds = ui.values[0];
        }
    });

    $("#esm-cost-slider").slider({
        range: false,
        min: 13,
        max: 120,
        values: [24],
        slide: function(event, ui) {
            console.log(ui.values[0]);
            $('#esm-cost').html(ui.values[0] + "$");
            rounds = ui.values[0];
        }
    });
});