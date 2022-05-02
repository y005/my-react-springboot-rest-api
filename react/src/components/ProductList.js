import React from "react";
import {Product} from "./Product";
import {GenreButton} from "./GenreButton";

export function ProductList({products=[], onAddClick, genres=[], onGenreClick}) {
    const onAllClick = ()=>{
      onGenreClick({genre: "all"})
    };

    return (
        <React.Fragment>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"><h5 className="flex-grow-0"><b>상품 목록</b></h5></th>
                    <th></th>
                    <th scope="col"><button className="btn btn-small btn-outline-dark" onClick={onAllClick}>All</button>
                    {genres.map(v =>
                        <GenreButton {...v} onGenreClick = {onGenreClick}/>
                    )}
                    </th>
                </tr>
                </thead>
            </table>
            <ul className="list-group products">
                {products.map(v =>
                    <li key={v.id} className="list-group-item d-flex mt-3">
                        <Product {...v} onAddClick={onAddClick}/>
                    </li>
                )}
            </ul>
        </React.Fragment>
    )
}