import React, { useEffect, useState } from 'react';
import { Image, Text, View, TouchableOpacity, ToastAndroid } from 'react-native';
import Icon from 'react-native-vector-icons/AntDesign';
import AsyncStorage from '@react-native-async-storage/async-storage';
import styles from './WhoAmIStyle';
import BASE_URL from '../config/Config';
import * as ImagePicker from 'expo-image-picker';

const WhoAmI = ({ navigation }: any) => {
  const [userData, setUserData] = useState<any>({});
  const [updateTrigger, setUpdateTrigger] = useState<boolean>(false); // Novo estado para acionar a atualização

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const token = await AsyncStorage.getItem('authorization');
        const response = await fetch(`${BASE_URL}/whoAmI`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        if (response.ok) {
          const data = await response.json();
          setUserData(data);
          console.log(data.image);
        } else {
          console.error('Erro ao obter informações do usuário');
        }
      } catch (error) {
        console.error('Erro ao buscar dados do usuário:', error);
      }
    };

    fetchUserData();
  }, [updateTrigger]);
  
  const handleUpdateAvatar = async () => {
    let permissionResult = await ImagePicker.requestMediaLibraryPermissionsAsync();
  
    if (permissionResult.granted === false) {
      ToastAndroid.show(
        'A permissão para acessar a galeria de imagens é necessária.',
        ToastAndroid.SHORT
      );
      return;
    }
  
    let result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      allowsEditing: true,
      aspect: [1, 1],
      quality: 1,
      base64: true,
    });
  
    if (result.assets && result.assets.length > 0 && result.assets[0].base64) {
      const base64 = result.assets[0].base64;
      console.log('Imagem em base64 ok ');
  
      const token = await AsyncStorage.getItem('authorization');
  
      try {
        const response = await fetch(`${BASE_URL}/putRegister`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
          body: JSON.stringify({ image: base64 }),
        });
  
        if (response.ok) {
          console.log('Avatar atualizado com sucesso!-');
          setUpdateTrigger((prevState) => !prevState);

          ToastAndroid.show('Imagem enviada com sucesso', ToastAndroid.SHORT);
        } else {
          console.error('Erro ao enviar a imagem:', response.status);
        }
      } catch (error) {
        console.error('Erro na requisição PUT:', error);
      }
    } else {
      console.error('Não foi possível obter a imagem selecionada ou o valor em base64 está vazio');
    }
  };
  
  
  return (
    <View style={styles.container}>
      <View style={styles.logo}>
        {userData && userData.image && userData.image !== "" ? (
          <Image
            source={{ uri: `data:image/png;base64,${userData.image}` }}
            style={{ width: 200, height: 200, borderRadius: 150 , marginBottom: 20}}
          />
        ) : (
          <Icon name="user" size={150} color="red" />
        )}
        <Text style={styles.logoName}>{userData.nome}</Text>
        <Text>{userData.email}</Text>
      </View>
  
      <TouchableOpacity style={styles.updateButton} onPress={handleUpdateAvatar}>
        <View style={styles.updateButtonText}>
          <Text style={{ color: 'white', fontSize: 20, fontWeight: 'bold' }}>Atualizar Avatar</Text>
        </View>
      </TouchableOpacity>
    </View>
  );
};

export default WhoAmI;
