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
                    <td scope="col"><h5 className="flex-grow-0 text-white"><b>상품 목록</b></h5></td>
                    <td></td>
                    <td scope="col"><button className="btn btn-small text-white btn-outline-light" onClick={onAllClick}>All</button>
                    {genres.map(v =>
                        <GenreButton {...v} onGenreClick = {onGenreClick}/>
                    )}
                    </td>
                </tr>
                </thead>
            </table>
                <div className="container">
                    <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                        {products.map(v =>
                                <Product {...v} onAddClick={onAddClick}/>
                        )}
                    </div>
                </div>
        </React.Fragment>
    )
}