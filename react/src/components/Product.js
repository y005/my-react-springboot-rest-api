import React from "react";

export function Product(props) {
    const id = props.id;
    const productName = props.name;
    const genre = props.genre;
    const price = props.price;
    const quantity = props.quantity;
    const img = props.img;

    const handleAddBtnClicked = ()=>{
        props.onAddClick(id);
    };

    return <>
        <div className="col-2"><img className="img-fluid" src={img} alt=""/></div>
        <div className="col">
            <div className="row text-muted">{genre}</div>
            <div className="row">{productName}</div>
        </div>
        <div className="col text-center price">{price}원</div>
        <div className="col text-info">{quantity}개 남음</div>
        <div className="col text-end action">
            <button onClick={handleAddBtnClicked} className="btn btn-small btn-outline-dark" >추가</button>
        </div>
    </>
}