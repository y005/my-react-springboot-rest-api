import React from "react";

export function GenreButton(props) {
    const genre = props.genre;

    const handleGenreBtnClicked = ()=>{
        props.onGenreClick({genre: genre});
    };

    return <>
        <button className="btn btn-small btn-outline-light text-white" onClick={handleGenreBtnClicked}>{genre}</button>
    </>
}

