import React, { useState } from 'react'
import { Text, TextInput, ToastAndroid, View } from 'react-native'
import Icon from 'react-native-vector-icons/AntDesign';
import styles from './CreateAccountStyle';
import { Button } from 'react-native-elements';
import { useNavigation } from '@react-navigation/native';
import Login from '../login/Login';
import BASE_URL from '../config/Config';

const CreateAccount = () => {
  const [fullName, setFullName] = useState('');
  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const navigation = useNavigation();

  const handleCreateAccount = async () => {
    if (!fullName || !email || !username || !password || !confirmPassword) {
      ToastAndroid.show(String('Por favor, preencha todos os campos'), ToastAndroid.SHORT);
    } else if (password.length < 6) {
      ToastAndroid.show(String('Senha muito curta, 6 caracteres no mínimo'), ToastAndroid.SHORT);
    } else if (password !== confirmPassword) {
      ToastAndroid.show(String('Senhas não conferem'), ToastAndroid.SHORT);
    } else {
      try {
        

        const response = await fetch(`${BASE_URL}/register`, {
          method: 'POST',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            nome: fullName,
            email: email,
            username: username,
            password: password,
          }),
        });

        if (response.ok) {
          ToastAndroid.show(String('CONTA CRIADA COM SUCESSO'), ToastAndroid.SHORT);
          navigation.navigate('login' as never); // 'login' as never apenas para parar de ficar o código vermelho
        } else {
          ToastAndroid.show(String('Erro ao criar a conta. Tente novamente.'), ToastAndroid.SHORT);
        }
      } catch (error) {
        ToastAndroid.show(String('Ocorreu um erro. Tente novamente mais tarde.'), ToastAndroid.SHORT);
        console.error('Erro:', error);
      }
    }
  };

  return (
    <View style={styles.container}>
      <View style={styles.logo}>
      <Icon name="rest" size={100} color={"red"}></Icon>
        <Text style={styles.logoName}> DRINKPLACE </Text>

      </View>
      
      <Text> Nome completo </Text>
      <TextInput
        style={styles.input}
        value={fullName}
        onChangeText={(text) => setFullName(text)}
      />
      <Text> Email </Text>
      <TextInput
        style={styles.input}
        value={email}
        onChangeText={(text) => setEmail(text)}
      />
      <Text> Username </Text>
      <TextInput
        style={styles.input}
        value={username}
        onChangeText={(text) => setUsername(text)}
      />
      <Text> Senha </Text>
      <TextInput
        secureTextEntry={true}
        style={styles.input}
        value={password}
        onChangeText={text => setPassword(text)}

      />
      <Text> Confirmação de senha</Text>
      <TextInput
        secureTextEntry={true}
        style={styles.input}
        value={confirmPassword}
        onChangeText={text => setConfirmPassword(text)}


      />
      <Text></Text>
      <Button title="CRIAR" buttonStyle={{ backgroundColor: '#FF999C' }} onPress={handleCreateAccount} />
      
     
    </View>
  )
}

export default CreateAccount
