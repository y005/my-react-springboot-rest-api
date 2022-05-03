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
                    <div className="col" >
                        <div className="card shadow-sm">
                            <div className="text-center card-header">
                                <img className="card-img-top" src={img} alt=""/>
                            </div>
                            <div className="card-body">
                                <div className="text-center">
                                    <div className="col text-muted">{genre}</div>
                                    <div className="col name">{productName}</div>
                                    <div className="col price">{price}원</div>
                                    <div className="col text-info info">{quantity}개 남음</div>
                                    <div className="col">
                                        <button onClick={handleAddBtnClicked} className="btn btn-small btn-outline-dark" >추가</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

    </>

}