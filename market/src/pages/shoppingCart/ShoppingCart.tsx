import React, { useState, useEffect } from 'react';
import { Text, View, FlatList, Image, TouchableOpacity, TextInput } from 'react-native';
import { styles } from './ShoppingCartStyle';
import AsyncStorage from '@react-native-async-storage/async-storage';
import Icon from 'react-native-vector-icons/FontAwesome'; // Importe o ícone apropriado
import BASE_URL from '../config/Config';


interface CartItem {
  id: number;
  name: string;
  price: number;
  quantity: number;
  image: string;
}

const ShoppingCart = () => {
  const [cartItems, setCartItems] = useState<CartItem[]>([]);

  useEffect(() => {
    fetchCartItems();
  }, []);

  const fetchCartItems = async () => {
    try {
      const token = await AsyncStorage.getItem('authorization');
      if (token) {
        const response = await fetch(`${BASE_URL}/shoppingCart`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        if (response.ok) {
          const data = await response.json();
          setCartItems(data);
        } else {
          console.error('Failed to fetch cart items');
        }
      } else {
        console.error('Token not found in AsyncStorage');
      }
    } catch (error) {
      console.error('Error fetching cart items:', error);
    }
  };

  const handleDeleteItem = async (itemId: number) => {
    try {
      const token = await AsyncStorage.getItem('authorization');
      if (token) {
        await fetch(`${BASE_URL}/shoppingCart/${itemId}`, {
          method: 'DELETE',
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        fetchCartItems(); // Atualize os dados após a exclusão
      } else {
        console.error('Token not found in AsyncStorage');
      }
    } catch (error) {
      console.error('Error deleting item:', error);
    }
  };

  const updateQuantity = async (itemId: number, action: string) => {
    try {
      const token = await AsyncStorage.getItem('authorization');
      if (!token) {
        console.error('Token not found in AsyncStorage');
        return;
      }
      if ((action === '-' && cartItems.find(item => item.id === itemId)?.quantity === 1) || action !== '-' && action !== '+') {
        return; // Não permita a redução para menos de 1 ou ações diferentes de '+' e '-'
      }

      await fetch(`${BASE_URL}/shoppingCart`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          product_id: itemId,
          action: action,
        }),
      });
      fetchCartItems(); // Atualize os dados após a atualização
    } catch (error) {
      console.error('Error updating quantity:', error);
    }
  };

  const renderCartItem = ({ item }: { item: CartItem }) => (
    <View style={styles.item}>
      <Image source={{ uri: item.image }} style={styles.image} />
      <View style={styles.itemDetails}>
        <Text>{item.name}</Text>
        <View style={styles.priceContainer}>
          <Text style={styles.priceText}>
            R$ {item.price.toFixed(2)}
          </Text>
        </View>
        <View style={styles.quantityContainer}>
          <TouchableOpacity onPress={() => updateQuantity(item.id, '-') } style={styles.quantityButton}>
            <Text>-</Text>
          </TouchableOpacity>
          <TextInput
            style={styles.quantityInput}
            value={item.quantity.toString()}
            keyboardType="numeric"
            editable={false}
          />
          <TouchableOpacity onPress={() => updateQuantity(item.id, '+')} style={styles.quantityButton}>
            <Text>+</Text>
          </TouchableOpacity>
          {/* Botão da lixeira */}
          <TouchableOpacity onPress={() => handleDeleteItem(item.id)} style={styles.trashButton}>
            <Icon name="trash" size={20} color="#666" />
          </TouchableOpacity>
          {/* Fim do botão da lixeira */}
        </View>
      </View>
    </View>
  );

  const calculateTotal = () => {
    return cartItems.reduce((total, item) => total + item.price, 0);
  };

  return (
    <View style={styles.container}>
      <FlatList
        data={cartItems}
        renderItem={renderCartItem}
        keyExtractor={(item) => item.id.toString()}
      />
      <View style={styles.totalContainer}>
        <Text style={styles.totalText}>Total: R$ {calculateTotal().toFixed(2)}</Text>
      </View>
    </View>
  );
};

export default ShoppingCart;
