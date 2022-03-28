$(function smth() {
    $('.like-toggle').click(function(){
        $(this).toggleClass('like-active');
        $(this).next().toggleClass('hidden');
    });
});

function showAlert() {
    $(this).toggleClass('like-active');
}