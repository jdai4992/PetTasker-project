//script for making review as stars
$.fn.stars = function() {
    return $(this).each(function() {
        // Get the value
        var val = parseFloat($(this).html());
        // Make sure that the value is in 0 - 5 range, multiply to get width
        var size = Math.max(0, (Math.min(5, val))) * 16;
        // Create stars holder
        var $span = $('<span />').width(size);
        // Replace the numerical value with stars
        $(this).html($span);
    });
}
$(function() {
    $('span.reviewStars').stars();
});

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//REFERENCES
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//References used were:
//
//CSS, T. (2017). Turn a number into star rating display using jQuery and CSS. [online] Stackoverflow.com. Available at: 
//https://stackoverflow.com/questions/1987524/turn-a-number-into-star-rating-display-using-jquery-and-css/1987545#1987545 [Accessed 22 Oct. 2017].