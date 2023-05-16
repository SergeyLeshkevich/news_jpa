function updateRating(idNews, action){
    let ratingForm=document.getElementById('sendRatingForm');
    ratingForm[0].value=idNews;
    ratingForm[1].value=action;
    ratingForm.submit();
}