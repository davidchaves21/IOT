import React, { useState } from 'react';
import { Button, Text, TextInput, ToastAndroid, View } from 'react-native';
import Icon from 'react-native-vector-icons/AntDesign';
import styles from './LoginStyle';
import AsyncStorage from '@react-native-async-storage/async-storage';
import BASE_URL from '../config/Config';



const Login = ({navigation}: any) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const goToPage = (path: string) => {
    navigation.navigate(path);  
  };
  const handleLogin = async () => {
    try { 
      const response = await fetch(`${BASE_URL}/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username: username, password: password }),
      });

      if (response.ok) {
        const token = response.headers.get('Authorization');
        if (token) {
          await AsyncStorage.setItem('authorization', token);
          goToPage('home');
        } else {
          ToastAndroid.show('Token not found in response', ToastAndroid.SHORT);
        }
      } else {
        ToastAndroid.show('Invalid username or password', ToastAndroid.SHORT);
      }
    } catch (error) {
      ToastAndroid.show('Error occurred while logging in', ToastAndroid.SHORT);
      console.error('Error:', error);
    }
  };

  
  return (
    <View style={styles.container}>

      <View style={styles.logo}>
        <Icon name="rest" size={150} color={"red"} ></Icon>
        <Text style={styles.logoName}>DRINKPLACE</Text>

      </View>

      <Text > Login </Text>
      <TextInput
        style={styles.input}
        value={username}
        onChangeText={(text) => setUsername(text)}
        placeholder="Username"
      />
      <Text> Password </Text>
      <TextInput
        secureTextEntry={true}
        style={styles.input}
        value={password}
        onChangeText={(text) => setPassword(text)}
        placeholder="Password"
      />
      <View style={styles.createForgotLink}>
        <Text onPress={() => goToPage("Criar Conta")} style={styles.link}>Create Account</Text>
        <Text onPress={() => goToPage("Recuperar Senha")} style={styles.link}>Forgot Password</Text>

      </View>
      <Button color={'#FF999C'} onPress={handleLogin} title="ACESSAR"></Button>
     
    </View>
  )
}

export default Login
