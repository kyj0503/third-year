from django.urls import path
from . import views

app_name = 'mysite'  # 앱 이름 지정

urlpatterns = [
    path('', views.index, name='index'),  # name='index' 추가
    path('<int:content_id>/', views.detail, name='detail'),  # name='detail' 추가
]
