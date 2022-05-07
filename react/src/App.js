import './App.css'
import 'bootstrap/dist/css/bootstrap.css'
import React, {useEffect, useState} from 'react';
import {ProductList} from "./components/ProductList";
import {Summary} from "./components/Summary";
import axios from "axios";
import alert from "bootstrap/js/src/alert";
import banner from './banner.png';

function App() {
  //판매 상품 정보 배열
  const [products, setProducts] = useState([]);
  //사용자가 선택한 상품 정보 배열
  const [items, setItems] = useState([]);
  //품목 장르 정보 배열
  const [genres, setGenres] = useState([]);

  const handleAddClicked = (id) => {
    const product = products.find(v=> v.id === id);
    const found = items.find(v=>v.id === id);
    //장바구니에 존재하는 product일 경우 선택 갯수를 기준 값에서 1 증가시키고
    //아닌 경우에는 product와 해당 아이템의 갯수를 1로 초기화 해준 정보를 넣어준다.
    const updatedItems =
        found? items.map(v=>(v.id===id)? {...v, count: v.count+1} : v) : [...items, {...product, count: 1}];
    setItems(updatedItems);
  };

  const handleOrderSubmit = (order) => {
    if (items.length === 0) {
        window.alert("최소 1개 이상의 아이템을 선택해주세요.");
    }
    else {
        axios.post("http://localhost:8080/api/v1/orders",{
            email: order.email,
            address: order.address,
            postcode: order.postcode,
            totalPrice: order.totalPrice,
            orderItems:
                items.map(v => ({
                    id: v.id,
                    genre: v.genre,
                    price: v.price,
                    quantity: v.count
                }))
        }).then(v => window.alert(v.data), e => window.alert(e.response.data) );
    }
  }

  const handleGenreClick = (genre) => {
    if (genre.genre === "all") {
        axios.get('http://localhost:8080/api/v1/products?size=6')
            .then(v=>setProducts(v.data));
    }
    else {
        axios.get('http://localhost:8080/api/v1/products?genre='+genre.genre+'&size=6')
            .then(v=>setProducts(v.data));
    }
  }

  const handleReset = () => {
        setItems([]);
  }

  useEffect(()=> {
      axios.get('http://localhost:8080/api/v1/products?size=6')
            .then(v=>setProducts(v.data));
      },[]);

  useEffect(()=> {
      axios.get('http://localhost:8080/api/v1/products/genres')
            .then(v=>setGenres(v.data));
      },[]);

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="text-center">
                    <img src={banner} height="200" alt=""/>
                </div>
            </div>
            <div className="card">
                <div className="row my-container">
                    <div className="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
                        <ProductList products={products} onAddClick={handleAddClicked} genres={genres} onGenreClick={handleGenreClick}/>
                    </div>
                    <div className="col-md-4 summary p-4" >
                        <Summary items={items} onOrderSubmit={handleOrderSubmit} onReset={handleReset}/>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default App;