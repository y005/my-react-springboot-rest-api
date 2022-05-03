import {SummaryItem} from "./SummaryItem";
import React, {useState} from "react";

export function Summary({items = [], onOrderSubmit, onReset}) {
    let total_price = items.reduce((prev, curr) => prev + (curr.price * curr.count), 0);
    const [order, setOrder] = useState({
        email:"", address:"", postcode:"", totalPrice: 0
    });
    const handleEmailInput = (e) => {setOrder({...order, email: e.target.value})};
    const handleAddressInput = (e) => {setOrder({...order, address: e.target.value})};
    const handlePostcodeInput = (e) => {setOrder({...order, postcode: e.target.value})};

    const handleSubmit = () => {
        if (order.address==="" || order.email === "" || order.postcode === "")
        {
            alert("이메일, 주소, 우편번호를 입력하지 않았습니다.")
        }
        else {
            onOrderSubmit({...order, totalPrice: total_price})
            console.log(order);
        }
    };

    const handleReset = () => {
      onReset();
    }

    return (
        <>
            <div>
                <h5 className="m-0 p-0 text-white"><b>구매 정보</b></h5>
            </div>
            <hr/>
            {items.map(v => <SummaryItem productName={v.name} count={v.count} key={v.id} />)}
            <form>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label text-white">이메일</label>
                    <input type="email" className="form-control mb-1 text-white" id="email" value={order.email} onChange={handleEmailInput}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="address" className="form-label text-white">주소</label>
                    <input type="text" className="form-control mb-1 text-white" id="address" value={order.address} onChange={handleAddressInput}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="postcode" className="form-label text-white">우편번호</label>
                    <input type="text" className="form-control text-white" id="postcode" value={order.postcode} onChange={handlePostcodeInput}/>
                </div>
                <div className="text-white">당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.</div>
            </form>
            <div className="row pt-2 pb-2 border-top">
                <h5 className="col text-white">총 금액</h5>
                <h5 className="col text-end text-white">{total_price}원</h5>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"><button className="btn btn-light text-black col-12" onClick={handleSubmit}>결제하기</button></th>
                    <th scope="col"><button className="btn btn-danger col-12" onClick={handleReset}>장바구니 초기화</button></th>
                </tr>
                </thead>
            </table>
        </>
    );
}