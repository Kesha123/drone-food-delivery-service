import * as bcrypt from 'bcrypt';
import { env } from '../config/env';

export async function encodePassword(rawPassword: string) {
  return bcrypt.hash(rawPassword, parseInt(env.BCRYPT_SALT));
}

export async function comparePassword(
  plainPassword: string,
  encryptedPassword: string,
) {
  return bcrypt.compare(plainPassword, encryptedPassword);
}
