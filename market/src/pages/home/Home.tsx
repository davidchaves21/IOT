import React, { useEffect, useState } from 'react';
import { ScrollView, Text, View, Image, TouchableOpacity, ToastAndroid } from 'react-native';
import { styles } from './HomeStyle';
import { useNavigation } from '@react-navigation/native';
import Icon from 'react-native-vector-icons/AntDesign';
import AsyncStorage from '@react-native-async-storage/async-storage';
import BASE_URL from '../config/Config';


interface Product {
  id: string;
  name: string;
  price: number;
  quantity: number;
  image: string;
}

const Home = ({ shoppingCart, setShoppingCart }: any) => {
  const [products, setProducts] = useState<Product[]>([]);
  const navigation: any = useNavigation();
  

  useEffect(() => {
    const fetchData = async () => {
      try {
        const token = await AsyncStorage.getItem('authorization');
        if (token) {
          const response = await fetch(`${BASE_URL}/feed`, {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          });
          if (response.ok) {
            const data = await response.json();
            setProducts(data);
          } else {
            console.error('Failed to fetch data');
          }
        } else {
          console.error('Token not found in AsyncStorage');
        }
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  const addToCart = async (productId: string) => {
    try {
      const token = await AsyncStorage.getItem('authorization');
      if (token) {
        const requestBody = {
          product_id: productId,
        };
  
        const response = await fetch(`${BASE_URL}/shoppingCart`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
          body: JSON.stringify(requestBody),
        });
  
        if (response.ok) {
          ToastAndroid.show('Item adicionado com sucesso ao carrinho!', ToastAndroid.SHORT);
          setShoppingCart([...shoppingCart, productId]);
        } else {
          const errorMessage = await response.json();
  
          // Extrai o código de erro usando expressão regular
          const errorMatch = errorMessage.message.match(/^\d{4}/);
          const errorCode = errorMatch ? errorMatch[0] : null;
  
          // Tratamento dos erros com base no código extraído da mensagem
          switch (errorCode) {
            case '2002':
              console.error('Produto já está no carrinho.');
              break;
            
            default:
              console.error('Produto já está no carrinho');
              break;
          }
        }
      } else {
        console.error('Token not found in AsyncStorage');
      }
    } catch (error) {
      console.error('Error adding item to cart:', error);
    }
  };

  const ProductCard = ({ product }: { product: Product }) => {
    const openToast = (message: string) => {
      ToastAndroid.show(message, 1000);
      setShoppingCart([...shoppingCart, product]);
    };
  
    return (
      <TouchableOpacity style={styles.productCard}>
        <Image source={{ uri: product.image }} style={styles.productImage} resizeMode="cover" />
        <View style={styles.productInfoContainer}>
          <Text style={styles.productName}>{product.name}</Text>
          <View style={styles.productInfoRow}>
            <Text style={{ fontSize: 16 }}>Preço: R$ {product.price}</Text>
            <Text>Quantidade: {product.quantity}</Text>
          </View>
          <TouchableOpacity
            style={styles.addToCartButton}
            onPress={() => addToCart(product.id)}
          >
            <Text style={styles.addToCartButtonText}>Adicionar ao carrinho</Text>
          </TouchableOpacity>
          
        </View>
      </TouchableOpacity>
    );
  };

  return (
    <>
      <ScrollView style={styles.container}>
        {products.map((product) => (
          <ProductCard key={product.id} product={product} />
        ))}
      </ScrollView>

      {/* Botão flutuante para Chat */}
      <TouchableOpacity
        style={styles.floatingButton}
        onPress={() => navigation.navigate('Chat' )}
      >
        <Icon name='wechat' size={40} color="#F590A0" />
      </TouchableOpacity>
    </>
  );
};

export default Home;
