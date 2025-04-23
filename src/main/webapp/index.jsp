<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <style>
      body {
        background: #ddd;
      }

      .card {
        margin: auto;
        max-width: 950px;
        min-width: 400px;
        max-height: 600px;
        min-height: 300px;
        width: 90%;
        box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
        border-radius: 1rem;
        border: transparent
      }

      .row {
        margin: 0
      }

      .title b {
        font-size: 1.5rem
      }

      img {
        width: 3.5rem
      }

      hr {
        margin-top: 1.25rem
      }

    </style>
    <title>Hello, world!</title>
</head>
<body class="container-fluid">
<div class="row justify-content-center m-4">
    <h1 class="text-center">Grids & Circle</h1>
</div>
<div class="card">
    <div class="row">
        <div class="mt-4 d-flex flex-column align-items-center p-3 pt-0">
            <h5 class="flex-grow-0"><b>인기 상품</b></h5>
            <div class="row justify-content-center text-center g-5 mt-3">
                <div class="col-auto">
                    <img src="#" alt="커피 이미지">
                    <p>고급 커피 이름1</p>
                    <p>커피 가격1</p>
                </div>
                <div class="col-auto">
                    <img src="#" alt="커피 이미지">
                    <p>고급 커피 이름2</p>
                    <p>커피 가격2</p>
                </div>
                <div class="col-auto">
                    <img src="#" alt="커피 이미지">
                    <p>고급 커피 이름3</p>
                    <p>커피 가격3</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>